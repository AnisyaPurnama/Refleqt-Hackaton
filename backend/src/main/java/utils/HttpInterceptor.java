package utils;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;

// HttpInterceptor implements the Interceptor interface of OkHttp
public class HttpInterceptor implements Interceptor {
    private static final String YELLOW_BOLD = "\033[1;33m";
    private static final String CYAN_BOLD = "\033[1;36m";

    // Define a logger to print all intercepted http traffic
    private static final Logger LOG = LoggerFactory.getLogger(HttpInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        /** 2 variables are defined:
         * - requestLog: contains headers & body of the request
         * - responseLog: contains headers, content type & body of the response
         */
        var requestLog = new HttpInterceptor.RequestLog(chain.request());
        var responseLog = new HttpInterceptor.ResponseLog(chain.proceed(requestLog.getOriginalRequest()));

        /** Use the logger to print the request headers + body and the response headers + body afeter formatting */
        LOG.info(YELLOW_BOLD+"REQUEST"+ requestLog.format());
        LOG.info(CYAN_BOLD+"RESPONSE"+ responseLog.format(Duration.ofNanos(responseLog.time - requestLog.time)));

        return responseLog.getOriginalResponse();
    }

    /**
     * Used in the format method of the requestLog and responseLog.
     * This function is defined in the parent class as it is used for both the requestLog as responseLog
     * If there is a request of response body, this will make sure a json formatter will be used.
     * If the body is empty, it will print a message "Empty body"
     * @param body --> This can be the request body or the response body.
     * @return json formatted body
     */
    private static String formatBody(String body) {
        return Optional.ofNullable(body)
                .map(jsonFormatter())
                .orElse("Empty body");
    }

    /**
     * @return json formatted String
     */
    private static Function<String, String> jsonFormatter() {
        return value -> {
            try {
                return new GsonBuilder().setPrettyPrinting().create().toJson(JsonParser.parseString(value));
            } catch (RuntimeException e) {
                return "Unable to pretty print: " + value;
            }
        };
    }

    /**
     * Class used for request interception
     */
    private static class RequestLog {
        private final long time = System.nanoTime();
        private final Request request;
        private final Headers header;
        private final String body;

        /** Constructor */
        private RequestLog(Request request) {
            this.request = request;
            this.header = request.headers();
            this.body = Optional.of(request).filter(isBodyPresent()).map(readBody()).orElse(null);
        }

        /** Getter */
        public Request getOriginalRequest() {
            return request;
        }

        /** Boolean-valued function to check if a body is present */
        private Predicate<Request> isBodyPresent() {
            return request -> Optional.of(request).map(Request::body).isPresent();
        }


        private Function<Request, String> readBody() {
            return request -> {
                try (var buffer = new Buffer()) {
                    request.newBuilder().build().body().writeTo(buffer);
                    return buffer.readString(request.body().contentType().charset(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new UncheckedIOException("Unable to read request body", e);
                }
            };
        }

        public String format() {
            return new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
                    .add("-> " + request.method() + " " + request.url())
                    .add("Headers:").add(request.headers().toString())
                    .add("Body:").add(formatBody(body))
                    .add("-> end request")
                    .toString();
        }
    }

    private static class ResponseLog {
        private final long time = System.nanoTime();
        private final Response response;
        private final Headers header;
        private final MediaType contentType;
        private final String body;

        private ResponseLog(Response response) {
            this.response = response;
            this.header = response.headers();
            this.contentType = Optional.ofNullable(response.body()).map(ResponseBody::contentType).orElse(null);
            this.body = Optional.of(response).filter(isBodyPresent()).map(readBody()).orElse(null);
        }

        public Response getOriginalResponse() {
            if (body != null) {
                return response.newBuilder().body(ResponseBody.create(contentType, body)).build();
            }
            return response;
        }

        private Predicate<Response> isBodyPresent() {
            return response -> Optional.of(response).map(Response::body).isPresent();
        }

        private Function<Response, String> readBody() {
            return response -> {
                try (var body = response.body()) {
                    return body.string();
                } catch (IOException e) {
                    throw new UncheckedIOException("Unable to read response body", e);
                }
            };
        }

        public String format(Duration duration) {
            return new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
                    .add("<- " + response.code() + " " + response.message() + " (" + duration.toMillis() + "ms)")
                    .add("Headers:").add(response.headers().toString())
                    .add("Body:").add(formatBody(body))
                    .add("<- end response")
                    .toString();
        }
    }

}

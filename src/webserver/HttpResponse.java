public class HttpResponse {
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    private String body;

    public String getBody() {
        return body;
    }

    public HttpResponse(HttpStatus status, String body) {
        this.status = status;
        this.body = body;
    }

    public HttpResponse(HttpStatus status) {
        this(status, "");
    }

    @Override
    public String toString() {
        return "HTTP/2.0 " +
                status.getCode() +
                " " +
                status.getText() +
                "\r\n\r\n" +
                body;
    }
}

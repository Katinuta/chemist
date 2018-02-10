package by.teplouhova.chemist.command;
/**
 * The Class CommandResult.
 */
public class CommandResult {

    /**
     * The Enum ResponseType.
     */
    public enum ResponseType{

        /** The forward. */
        FORWARD,
        /** The redirect. */
        REDIRECT
    }

    /** The response type. */
    private ResponseType responseType;

    /** The page. */
    private String page;

    /**
     * Instantiates a new command result.
     */
    public CommandResult() {
    }

    /**
     * Instantiates a new command result.
     *
     * @param responseType the response type
     * @param page the page
     */
    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    /**
     * Gets the response type.
     *
     * @return the response type
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Sets the response type.
     *
     * @param responseType the new response type
     */
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    /**
     * Gets the page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the page.
     *
     * @param page the new page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "CommandResult{" +
                "responseType=" + responseType +
                ", page='" + page + '\'' +
                '}';
    }
}

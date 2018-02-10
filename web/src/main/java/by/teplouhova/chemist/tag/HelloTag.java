package by.teplouhova.chemist.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The Class HelloTag.
 */
public class HelloTag extends TagSupport {

    /**
     * The name.
     */
    private String name;

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Do start tag.
     *
     * @return the int
     * @throws JspException the jsp exception
     */
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<span>" + "Welcome,  " + name + "</span>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}

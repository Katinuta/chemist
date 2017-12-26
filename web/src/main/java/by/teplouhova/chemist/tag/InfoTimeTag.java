package by.teplouhova.chemist.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class InfoTimeTag extends TagSupport {

    public int doStartTag() throws JspException {
        String date = "<hr> Date: <b>" + LocalDate.now() + "</b></hr>";
        String locale = "<hr> Date: <b>" + Locale.getDefault() + "</b></hr>";
        JspWriter out = pageContext.getOut();
        try {
            out.write(date + locale);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}

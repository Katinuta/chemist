package by.teplouhova.chemist.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class HelloTag extends TagSupport {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public int doStartTag() throws JspException {
//        String brand = "<span>" + "Chemist" + "<span></hr>";
//        String locale = "<hr> Date: <b>" + Locale.getDefault() + "</b></hr>";
//        JspWriter out = pageContext.getOut();
        try {
            pageContext.getOut().write("<span>" + "Welcome,  "+name+ "</span>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}

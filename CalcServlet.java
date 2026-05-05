import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CalcServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get parameters safely
        String num1Str = req.getParameter("num1");
        String num2Str = req.getParameter("num2");
        String op = req.getParameter("operation");

        if (num1Str == null || num2Str == null || op == null) {
            out.println("<h3>Error: Missing input values!</h3>");
            return;
        }

        try {
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);
            int result = 0;

            switch (op) {
                case "add": result = num1 + num2; break;
                case "sub": result = num1 - num2; break;
                case "mul": result = num1 * num2; break;
                case "div":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        out.println("<h3>Error: Division by zero!</h3>");
                        return;
                    }
                    break;
                default:
                    out.println("<h3>Error: Invalid operation!</h3>");
                    return;
            }

            out.println("<h2>Result: " + result + "</h2>");

        } catch (NumberFormatException e) {
            out.println("<h3>Error: Please enter valid numbers!</h3>");
        }
    }
}
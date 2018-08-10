package kdf.web.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * ProcessImageServlet.
 *
 * @author Lingo
 */
public class ProcessImageServlet extends HttpServlet {
    /** * serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * 生成图片，并发送给客户端.
     *
     * @param request 请求
     * @param response 相应
     * @throws ServletException servlet异常
     * @throws IOException io异常
     */
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        String id = path.substring(path.lastIndexOf("/") + 1,
                path.lastIndexOf("."));

        long processDefinitionId = Long.parseLong(id);
        //JbpmContext jbpmContext = JbpmUtils.getJbpmContext(request);

        //ProcessDefinition processDefinition = jbpmContext.getGraphSession()
                                           //              .loadProcessDefinition(processDefinitionId);
        //byte[] bytes = processDefinition.getFileDefinition()
                                //        .getBytes("processimage.jpg");
        response.setContentType("image/jpg");

        OutputStream out = response.getOutputStream();
        //out.write(bytes);
        out.flush();
    }
}

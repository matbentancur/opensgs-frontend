package opensgs.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import opensgs.web.actions.usuarios.AutenticacionAction;
import org.apache.struts2.StrutsStatics;

public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        final ActionContext actionContext = invocation.getInvocationContext();
        
        HttpServletRequest request = (HttpServletRequest) actionContext.get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        // Is there a "user" object stored in the user's HttpSession?
        Object dtSesion = session.getAttribute("dtSesion");
        if (dtSesion == null) {
            /* The user is attempting to log in. */
            if (invocation.getAction().getClass().equals(AutenticacionAction.class)) {
                return invocation.invoke();
            }
            return "login";
        } else {
            return invocation.invoke();
        }
    }

}

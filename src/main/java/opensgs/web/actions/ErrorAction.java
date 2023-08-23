package opensgs.web.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

@Action(value = "ErrorAction", results = {
        @Result(location = "error.jsp"),
        @Result(name="input", location="error.jsp"),
        @Result(name="error", location = "error.jsp")
    }
)
public class ErrorAction extends BaseAction {   
       
    @Override
    public String execute() throws Exception {
        addActionError(this.getText("Error.generic"));
        return ERROR;
    }

}

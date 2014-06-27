package Group;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ICommand {
	public Object proecssCommand(HttpServletRequest req,
			HttpServletResponse res) throws SecurityException,IOException;
}

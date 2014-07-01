package homepage.board;

public class CommandFactory {
	//Singleton pattern
	private CommandFactory(){}
	private static CommandFactory instance = new CommandFactory();
	public static CommandFactory getInstance(){
		return instance;
	}
	//Singleton pattern
	
	//Factory pattern
	public ICommand createCommand(String command){ 
		//System.out.println(command);
		if(command.equals("LIKE")){
			return new LikeModel();
		}
		else if(command.equals("UNLIKE")){
			return new UnlikeModel();
		}
		else if(command.equals("DELETE")){
			return new BoardDelete();
		}
		else if(command.equals("UPDATE")){
			return new BoardUpdate();
		}
		else if(command.equals("REPLYPOST")){
			return new ReplyPost();
		}
		else if(command.equals("REPLYDELETE")){
			return new ReplyDelete();
		}
		else if(command.equals("MAINSTART")){
			return new MainStart();
		}
		else if(command.equals("SENDEMAIL")){
			return new FindId();
		}
		else if(command.equals("UPDATEPW")){
	         return new UpdatePassword();
	    }
		else{
			return null;
		}
		
	}
	
}

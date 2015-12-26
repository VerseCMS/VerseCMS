package cms

class Users {

	String name
	String username
	String email
	Boolean isAdmin
	String password
  String confirmPassword
	
	
    static constraints = {
		
		name blank: false
		email blank: false
		username blank: false
		password blank: false, nullable: false, password: true 
    confirmPassword blank: false, nullable: false, password: true
		isAdmin blank: false
    }
}

package cms

class Users {

	String name
	String username
	String email
	Boolean isAdmin
	String password
	
	
    static constraints = {
		
		name blank: false
		email blank: false
		username blank: false
		password blank: false
		isAdmin blank: false
    }
}

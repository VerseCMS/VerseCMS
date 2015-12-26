package cms

class Page {
	
	String header
	String body
	Users author
	String template

    static constraints = {
    
		header blank:false
		body blank:false, widget: 'textarea'
		author blank: false
		template blank: false
		
	
	}
}

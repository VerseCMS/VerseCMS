package cms

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PageController {
    static scaffold = Page
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Page.list(params), model:[pageCount: Page.count()]
    }
	
	def about() {
		
	}

    def show(Page page) {
        respond page
    }

    def create() {
        
        respond new Page(params)
    }

    @Transactional
    def save(Page page) {
        if (page == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (page.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond page.errors, view:'create'
            return
        }

        page.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'page.label', default: 'Page'), page.id])
                redirect page
            }
            '*' { respond page, [status: CREATED] }
        }
    }

    def edit(Page page) {
        respond page
    }

    @Transactional
    def update(Page page) {
        if (page == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (page.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond page.errors, view:'edit'
            return
        }

        page.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'page.label', default: 'Page'), page.id])
                redirect page
            }
            '*'{ respond page, [status: OK] }
        }
    }

    @Transactional
    def delete(Page page) {

        if (page == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        page.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'page.label', default: 'Page'), page.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'Page'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

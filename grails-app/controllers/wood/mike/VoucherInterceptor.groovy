package wood.mike

class VoucherInterceptor {

	VoucherInterceptor() {
		match action: ~/(add|delete)/
	}

	boolean before() {
		logFlashMessages()
		clearFlashMessages()
		logFlashMessages()
		true
	}

	private void logFlashMessages() {
		flash.each {
			println it
		}
	}

	private void clearFlashMessages() {
		flash.message = ""
		flash.error = ""
		flash.searchError = ""
	}
}
package wood.mike

class VoucherController {

    def index() {
        log.info "In index"
        flash.message = 'Hello message'
        flash.searchError = 'Hello searchError'
        render "Voucher index"
    }

    def add() {
        log.info "In add"
        render "Voucher add"
    }

    def delete() {
        log.info "In delete"
        render "Voucher delete"
    }
}

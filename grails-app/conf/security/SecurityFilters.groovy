package security

class SecurityFilters {

    def filters = {
        all(controller:'*', action:'*', controllerExclude: "") {
//
        }
    }
}

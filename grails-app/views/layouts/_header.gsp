<nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top">
    <div class="container-fluid">
        <div class="navbar-wrapper">
                <g:img class="img-fluid" dir="images" file="qilqaLogo.png" style="width: 100%; height: 200%;" alt="${message(code: "login.welcome", default: "Bienvenido")} ${session.loggedUser?.firstName} ${session.loggedUser?.lastName}"/>
        </div>
        %{--<button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">--}%
            %{--<span class="sr-only">Toggle navigation</span>--}%
            %{--<span class="navbar-toggler-icon icon-bar"></span>--}%
            %{--<span class="navbar-toggler-icon icon-bar"></span>--}%
            %{--<span class="navbar-toggler-icon icon-bar"></span>--}%
        %{--</button>--}%
        %{--<div class="collapse navbar-collapse justify-content-end">--}%
    %{--<g:if test="${session.permisos.contains("APOSTILLAS_NEW_MENU")}">--}%
        %{--<g:remoteLink class="minimalize-styl-2 col-md-1" controller="apostilla" action="create" update="trabajo" accesskey='Z'>--}%
            %{--<i class="material-icons" title="Nueva Apostilla Shift+Alt+A">receipt</i>--}%
        %{--</g:remoteLink>--}%
    %{--</g:if>--}%
    %{--<g:if test="${session.permisos.contains("LEGALIZACION_CREATE_MENU")}">--}%
            %{--<g:remoteLink class=" minimalize-styl-2 col-md-1" controller="legalizacion" action="create" update="trabajo"--}%
                          %{--accesskey='Z'>--}%
                %{--<i class="material-icons" title="Nueva legalizacion  Shift+Alt+L">gavel</i>--}%
            %{--</g:remoteLink>--}%
    %{--</g:if>--}%
    %{--<g:if test="${session.permisos.contains("PAPELERIA_CREATE_MENU")}">--}%
    %{--<g:remoteLink class=" minimalize-styl-2 col-md-1" controller="papeleria" action="create" update="trabajo"--}%
                          %{--accesskey='Z'>--}%
                %{--<i class="material-icons" title="Nueva papeleria  Shift+Alt+P">import_contacts</i>--}%
            %{--</g:remoteLink>--}%
    %{--</g:if>--}%
            %{--${session.loggedUser.firstName} - ${session.delegacion?.descripcion} -${session?.registro?.descripcion} ${session?.registro?.numero}--}%
            %{--<form class="navbar-form"></form>--}%
            %{--<ul class="navbar-nav">--}%
                %{--<li class="nav-item dropdown">--}%
                    %{--<a class="nav-link" href="#" id="userMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--}%
                        %{--<i class="material-icons">settings</i>--}%
                        %{--<p class="d-lg-none d-md-block"><g:message code="profile.label" default="Perfil"/></p>--}%
                    %{--</a>--}%
                    %{--<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">--}%
                        %{--<g:remoteLink class="dropdown-item" controller="usuario" action="changePassword" params="[id: session.loggedUser.id]" update="modal_view_password"><g:message code="profile.password.change.button" default="Cambiar contraseña"/></g:remoteLink>--}%
                        %{--<g:link class="dropdown-item" controller="verify" action="logoff"><g:message code="profile.logoff.label" default="Cerrar sesion"/></g:link>--}%
                    %{--</div>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
</nav>
<div id="modal_view_password"></div>
<div id="errorsPass"></div>

<script>
    function changePageTitle(title) {
        $("#pageTitle").html(title)
    }
</script>
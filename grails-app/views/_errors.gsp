<script>
    <g:if test="${message}">
    $.notify({
        icon: "info",
        message: "${message.replaceAll("\"", "")}"
    }, {
        type: "danger",
        timer: 10000,
        placement: {
            from: "top",
            align: "right"
        }
    })
    </g:if>
    <g:else>
    <g:hasErrors bean="${failObject}">
    <g:eachError bean="${failObject}" var="error">
    $.notify({
        icon: "info",
        message: "${message(error: error).replaceAll("\"", "")}"
    }, {
        type: "danger",
        timer: 10000,
        placement: {
            from: "top",
            align: "right"
        }
    })
    </g:eachError>
    </g:hasErrors>
    </g:else>
</script>
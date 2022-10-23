<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lock</title>
    <%@include file="head.jsp" %>
  </head>
  <body>
    <div class="hldin" align="center">
      <h2 class="rtrh2">
        We do not support landscape mode or desktop mode as of now<br />please
        rotate your device
      </h2>
      <img
        class="rtrimg"
        src="${pageContext.request.contextPath}/images/static-images/automatic-rotation-svgrepo-com.svg"
        style="transform: rotate(-90deg)"
      />
    </div>
  </body>
</html>

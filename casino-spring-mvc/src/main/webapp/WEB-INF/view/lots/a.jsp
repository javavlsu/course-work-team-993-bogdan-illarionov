<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<html>
  <head>
    <title>GAME</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <h1>Slots!</h1>
      <div class="row h-50">
        <div class="col bg-primary">GAME</div>
      </div>

      <br/>
      <div class="row form-group">
        <div class="col-md-4">
          <input class="form-control" type="number" id="sizeOfBet"/>
        </div>
        <div class="col-md-4">
          <select name="game-results" id="game-results-select">
            <option value="">--Please choose an option--</option>
            <option value="red">Red</option>
            <option value="black">Black</option>
          </select>
        </div>
        <div class="col-md-4">
          <button class="btn btn-primary" type="button">
            Bet
          </button>
        </div>
      </div>
      <div class="row h-25">
        <div class="col bg-info">INFO</div>
      </div>
    </main>
  </body>
</html>

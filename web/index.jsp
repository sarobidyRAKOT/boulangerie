<%@page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<% 
    String daty = LocalDate.now().toString();
    String but = (String) request.getAttribute("but");

    if (but == null || but.isEmpty()) {
        but = "pages/dashboard.jsp";
    }

%>


<jsp:include page="/pages/layout/header.jsp"/>
<jsp:include page="/pages/layout/_navbar.jsp"/>
<div class="container-fluid page-body-wrapper">
    <jsp:include page="/pages/layout/_sidebar.jsp"/>
    <div class="main-panel">
        <div class="content-wrapper">

            <div class="row">
            <div class="col-md-12 grid-margin">
              <div class="row">
                <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                  <h2 class="font-weight-bold">Bienvenu chez BOULANGERIE</h2>
                  <h6 class="font-weight-normal mb-0">All systems are running smoothly! You have <span class="text-primary">3 unread alerts!</span></h6>
                </div>
                <div class="col-12 col-xl-4">
                 <div class="justify-content-end d-flex">
                  <div class="dropdown flex-md-grow-1 flex-xl-grow-0">
                    <button class="btn btn-sm btn-light bg-white dropdown-toggle" type="button" id="dropdownMenuDate2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                     <i class="mdi mdi-calendar"></i> Aujourd'hui (<%=daty%>)
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuDate2">
                      <a class="dropdown-item" href="#">Janvier - March</a>
                      <a class="dropdown-item" href="#">Février - June</a>
                      <a class="dropdown-item" href="#">June - August</a>
                      <a class="dropdown-item" href="#">August - November</a>
                    </div>
                  </div>
                 </div>
                </div>
              </div>
            </div>
            </div>
            <jsp:include page="<%=but%>"/> <%--CONTENU DU SITE--%>
        </div>
    </div>
</div>
<%-- <jsp:include page="/pages/p.jsp"/> --%>
<jsp:include page="/pages/layout/footer.jsp"/>
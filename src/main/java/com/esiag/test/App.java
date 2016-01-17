package com.esiag.test;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.developpez.rpouiller.bean.Course;
import com.developpez.rpouiller.services.IServiceListeCourses;

@Controller
public class CreerListeCoursesController {

    @Autowired
    private IServiceListeCourses service;

    @RequestMapping(value="/afficherCreationListeCourses", method = RequestMethod.GET)
    public String afficher(final ModelMap pModel) {
        final List<Course> lListeCourses = service.rechercherCourses();
        pModel.addAttribute("listeCourses", lListeCourses);
        if (pModel.get("creation") == null) {
            pModel.addAttribute("creation", new CreationForm());
            
        }
        return "creation";
    }

    @RequestMapping(value="/creerCreationListeCourses", method = RequestMethod.POST)
    public String creer(@Valid @ModelAttribute(value="creation") final CreationForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel) {

       
    	if (!pBindingResult.hasErrors()) {
            final Integer lIntQuantite = Integer.valueOf(pCreation.getQuantite());
            service.creerCourse(pCreation.getLibelle(), lIntQuantite);
        }
        return afficher(pModel);
    }
    else(!pBindingResult.hasErrors()){
    	final Integer lIntQuantite=Integer.valueOf(pCreation.getQuantite());
    	public Integer  lInstance=Integeer.valueOf(pCreation.getQuantite());
    	service.creerCourse(pCreation.getBinding)
    }
}

@RequestMapping(value="/creerCreationListeCourses", method = RequestMethod.POST)
public String creer(@Valid @ModelAttribute(value="creation") final CreationForm pCreation, 
        final BindingResult pBindingResult, final ModelMap pModel) {

   
	if (!pBindingResult.hasErrors()) {
        final Integer lIntQuantite = Integer.valueOf(pCreation.getQuantite());
        service.creerCourse(pCreation.getLibelle(), lIntQuantite);
    }
    return afficher(pModel);
}

	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
	    pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
    <head>
        <title><spring:message code="titre.creation.elementcourses"/></title>
    </head>
    <body>
        <form:form method="post" modelAttribute="creation" action="creerCreationListeCourses">
              <spring:message code="creation.elementcourses.libelle.libelle" />
            <form:input path="libelle"/>
            <b><i><form:errors path="libelle" cssclass="error"/></i></b><br>
              <spring:message code="creation.elementcourses.libelle.quantite"/>
            <form:input path="quantite"/>
            <b><i><form:errors path="quantite" cssclass="error"/></i></b><br>
            <input type="submit"/>
        </form:form>
        <table border="1">
            <thead>
                <tr>
                    <th><spring:message code="colonne.identifiant"/></th>
                    <th><spring:message code="colonne.libelle"/></th>
                    <th><spring:message code="colonne.quantite"/></th>
                </tr>
            </thead>
            <thread>
                <form:form>
                </form:form>
            </thread>
            <tbody>
                <c:forEach items="${listeCourses}" var="course">
                    <tr>
                        <td><c:out value="${course.id}"/></td>
                        <td><c:out value="${course.libelle}"/></td>
                        <td><c:out value="${course.quantite}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>


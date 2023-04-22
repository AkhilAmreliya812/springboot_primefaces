package com.logicbig.example;

import java.io.IOException;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.type.YesNoType;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class StudentBean {
	@Autowired
	private StudentRepository studentRepository;
	private List<Student> students;
	StudentService studentService;
	Student student;
	private Long searchStudentId;
	private boolean extraInfoPanel = false;
	private boolean showSingleStudent = false;
	private boolean showAllStudent = false;
	private boolean viewPanel = false;
	private boolean disableSaveButton = true;
	private boolean disableSaveExtraInfoButton = true;
	private boolean extraInfoFields = false;

	public StudentBean(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostConstruct
	public void init() {
		student = new Student();
		students = studentRepository.findAll();
		getStudents();
	}

	public void addNew() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("student.xhtml");
		student = new Student();
		extraInfoPanel = false;
		viewPanel = false;
		disableSaveButton = true;
		disableSaveExtraInfoButton = true;
		extraInfoFields = false;
		init();
	}

	public void ErrorPage() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("error.xhtml");
		searchData();
	}

	public void back() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("index.xhtml");
		searchData();
	}

	public void openData() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("student.xhtml");
		String city = student.getCity();
		String degree = student.getDegree();

		disableSaveButton = false;
		disableSaveExtraInfoButton = false;
		extraInfoFields = false;
		if (city != null && degree != null) {
			extraInfoPanel = true;
			viewPanel = true;
			disableSaveExtraInfoButton = false;
			extraInfoFields = true;
		} else if (city == null || degree == null) {
			extraInfoPanel = true;
			viewPanel = false;
			disableSaveExtraInfoButton = true;
		}
	}

	public void saveStudentBasicInfo() {
		student.setIsViewMode(Boolean.TRUE);
		studentService.saveStudentrecord(student);
		showAllStudent = true;
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success Message",
				"Student basic information saved successfully!.");
		PrimeFaces.current().dialog().showMessageDynamic(message);
		extraInfoPanel = true;
		disableSaveButton = false;
	}

	public void saveStudentExtraInfo() {
		studentService.saveStudentrecord(student);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success Message",
				"Student extra information updated successfully!.");
		PrimeFaces.current().dialog().showMessageDynamic(message);

		viewPanel = true;
		disableSaveExtraInfoButton = false;
		extraInfoFields = true;
	}

	public void searchData() throws IOException {
		Boolean yesNo = student.getActive();

		System.out.println("yes NO value ==> " + yesNo);
		if ((yesNo != null) && (searchStudentId == null)) {
			System.out.println("Yeno not null and searchStudentId null");

			students = studentService.getStudentByStatus(yesNo, searchStudentId);
			System.out.println("After " + students);
			
			showAllStudent = true;
			showSingleStudent = false;

		} else if ((yesNo != null) && (searchStudentId != null)) {
			System.out.println("Yeno not null and searchStudentId not null (both are not null)");

			student = studentService.getDataByActiveAndId(yesNo, searchStudentId);

			System.out.println("Student Student =====> " + student);

			showSingleStudent = true;
			showAllStudent = false;

		} else if ((yesNo == null) && (searchStudentId != null)) {

			student = studentService.getStudentById(searchStudentId);
			System.out.println("Student Name ==> " + student.getName());

			showSingleStudent = true;
			showAllStudent = false;
		} else {
			System.out.println("All data open");
			students = studentRepository.findAll();

			showAllStudent = true;
			showSingleStudent = false;
		}

//		String a = studentService.getDataGlobalQueryForAll(yesNo, searchStudentId);
//		System.out.println("Query ==> " + a);
//
//		students = studentService.getAllDataByQuery(yesNo, searchStudentId);

	}

	/*
	 * public void searchData() { Boolean yesNo = student.getActive();
	 * 
	 * System.out.println("Bean Yes No ===> " + yesNo);
	 * System.out.println("searchStudentId ===> " + searchStudentId);
	 * 
	 * String student = studentService.getStudent(yesNo, searchStudentId); }
	 */

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Long getSearchStudentId() {
		return searchStudentId;
	}

	public void setSearchStudentId(Long searchStudentId) {
		this.searchStudentId = searchStudentId;
	}

	public StudentRepository getStudentRepository() {
		return studentRepository;
	}

	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public boolean isExtraInfoPanel() {
		return extraInfoPanel;
	}

	public void setExtraInfoPanel(boolean extraInfoPanel) {
		this.extraInfoPanel = extraInfoPanel;
	}

	public boolean isShowSingleStudent() {
		return showSingleStudent;
	}

	public void setShowSingleStudent(boolean showSingleStudent) {
		this.showSingleStudent = showSingleStudent;
	}

	public boolean isShowAllStudent() {
		return showAllStudent;
	}

	public void setShowAllStudent(boolean showAllStudent) {
		this.showAllStudent = showAllStudent;
	}

	public boolean isViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(boolean viewPanel) {
		this.viewPanel = viewPanel;
	}

	public boolean isDisableSaveButton() {
		return disableSaveButton;
	}

	public void setDisableSaveButton(boolean disableSaveButton) {
		this.disableSaveButton = disableSaveButton;
	}

	public boolean isDisableSaveExtraInfoButton() {
		return disableSaveExtraInfoButton;
	}

	public void setDisableSaveExtraInfoButton(boolean disableSaveExtraInfoButton) {
		this.disableSaveExtraInfoButton = disableSaveExtraInfoButton;
	}

	public boolean isExtraInfoFields() {
		return extraInfoFields;
	}

	public void setExtraInfoFields(boolean extraInfoFields) {
		this.extraInfoFields = extraInfoFields;
	}

}

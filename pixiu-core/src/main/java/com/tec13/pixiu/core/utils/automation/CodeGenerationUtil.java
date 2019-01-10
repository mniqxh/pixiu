package com.tec13.pixiu.core.utils.automation;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.tec13.pixiu.core.jpa.BaseRepository;
import com.tec13.pixiu.core.jpa.BaseService;

public class CodeGenerationUtil {

	private static final String STR_REPOSITORY = "Repository";
	private static final String STR_SERVICE = "Service";
	private static final String DOT = ".";


	public void generateCode(Class<?> domainClazz,Appendable out) throws Exception {
		
		JavaFile repoFile = JavaFile.builder(getReposiotryPackage(domainClazz), generateRepository(domainClazz)).build();
		repoFile.writeTo(out);
		
		JavaFile serviceFile = JavaFile.builder(getServicePackage(domainClazz), generateService(domainClazz)).build();
		serviceFile.writeTo(out);

	}
	
	public TypeSpec generateRepository(Class<?> domainClazz ) {
		ParameterizedTypeName superRepository = ParameterizedTypeName.get(BaseRepository.class, domainClazz,Long.class);
		TypeSpec repository = TypeSpec.interfaceBuilder(domainClazz.getSimpleName()+STR_REPOSITORY)
			    .addModifiers(Modifier.PUBLIC)
			    .addAnnotation(Repository.class)
			    .addSuperinterface(superRepository)
			    .build();
		return repository;
	}
	
	public TypeSpec generateService(Class<?> domainClazz ) throws ClassNotFoundException {
		

		ParameterizedTypeName superService = ParameterizedTypeName.get(BaseService.class, domainClazz,Long.class);
		TypeSpec repositoryType = generateRepository(domainClazz);
		
		TypeVariableName tvb = TypeVariableName.get(getReposiotryPackage(domainClazz)+DOT+repositoryType.name);
		
		FieldSpec repository = FieldSpec.builder(tvb, convertToCannoicalForm(repositoryType.name), Modifier.PRIVATE).addAnnotation(Autowired.class).build();
		
		MethodSpec methodGetRepository = MethodSpec.methodBuilder("getRepository")
				.addAnnotation(Override.class)
				.addStatement("return "+repository.name)
				.returns(tvb)
				.addModifiers(Modifier.PROTECTED)
				.build();
		
		TypeSpec service = TypeSpec.classBuilder(domainClazz.getSimpleName()+STR_SERVICE)
			    .addModifiers(Modifier.PUBLIC)
			    .addAnnotation(Service.class)
			    .superclass(superService)
			    .addMethod(methodGetRepository)
			    .addField(repository)
			    .build();
		
		return service;
	}
	
	private String getBasePackage(Class<?> domainClazz ) {
		String domainPackageStr = domainClazz.getPackage().getName();
		String basePackage = domainPackageStr.substring(0, domainPackageStr.lastIndexOf("."));
		return basePackage;
	}
	
	private String getReposiotryPackage(Class<?> domainClazz ) {
		return getBasePackage(domainClazz)+DOT+STR_REPOSITORY.toLowerCase();
	}
	
	private String getServicePackage(Class<?> domainClazz ) {
		return getBasePackage(domainClazz)+DOT+STR_SERVICE.toLowerCase();
	}
	
	private String convertToCannoicalForm(String className) {
		return className.toLowerCase().charAt(0)+className.substring(1);
		
	}
}

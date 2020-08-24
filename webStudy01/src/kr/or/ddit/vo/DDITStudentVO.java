package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

/*
 * ValueObject : VO - DataTransferObject : DTO-Model-Bean
 * 자바 빈 규약에 정의 된 내용 
 * 1. vo는 값을 가질수 잇는 속성  -->  property, filed
 * 2. 캡슐화된 데이터에 접근할 방법 제공  (getter, setter)
 * 			get[set] PropertyName - camel 표기방식 ex) careerDate
 * 3. 상태 확인 메소드 제공  (toString) --객체안에 들어있는 내용을 확인하기 위해서 	
 * 4. 객체 상태 비교 메소드 (equals)
 * 5. 데이터 전송시 직렬화 가능하도록 
 * 
 */
public class DDITStudentVO implements Serializable{
//	이름, 생년월일, 나이, 학력, 성별, 자격증, 경력사항  변수 만들기 
	private String code; // PK(식별성) 학번을 만들어줌 db에 가져올 목적 	
	private String name;
	private String birthday;
	private int age;
	private String grade;
	private String gen;
	private List<String> license; // 하나의 VO가 많은 LICENSE를 가짐  HAS MANY관계, 베열에서 리스트로 바뀌었을때 문제점/ dabtabase 용 
	private String[] lic_codes; // request parameter용
	private String career;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public List<String> getLicense() {
		return license;
	}
	public void setLicense(List<String> license) {
		this.license = license;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String[] getLic_codes() {
		return lic_codes;
	}

	public void setLic_codes(String[] lic_codes) {
		this.lic_codes = lic_codes;
	}
	
	
	@Override
	public String toString() {
		return "DDITStudentVO [name=" + name + ", birthday=" + birthday + ", age=" + age + ", grade=" + grade + ", gen="
				+ gen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	
	// 비교할거  오버라이드 받기 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DDITStudentVO other = (DDITStudentVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}

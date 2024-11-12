package com.karthick.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Categorys {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@jakarta.validation.constraints.NotNull
	@Column(unique = true)
	private String categoryId;
	
	private String name;
	
	@ManyToOne
	private Categorys parenCategorys;
	
	@jakarta.validation.constraints.NotNull
	private Integer level;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categorys getParenCategorys() {
		return parenCategorys;
	}

	public void setParenCategorys(Categorys parenCategorys) {
		this.parenCategorys = parenCategorys;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}

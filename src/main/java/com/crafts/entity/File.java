package com.crafts.entity;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="files")
public class File {

	@Id
	@Column(name="fileId")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String fileId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Lob
	@Column(name="file")
	private byte[] file;
	
	
	//constructors
	
	public File() {}
	
	public File(String name, String type, byte[] file) {
		super();
		this.name = name;
		this.type = type;
		this.file = file;
	}
	
	//getters and setters

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Files [fileId=" + fileId + ", name=" + name + ", type=" + type + ", file=" + Arrays.toString(file)
				+ "]";
	}
	
	
}

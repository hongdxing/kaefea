package org.kaefea.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperateResult {

	boolean success;
	
	String msg;
	
}

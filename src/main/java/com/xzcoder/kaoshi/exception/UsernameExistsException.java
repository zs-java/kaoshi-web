package com.xzcoder.kaoshi.exception;

/**
 * UsernameExistsException
 * 用户名重复异常，导致不能添加用户
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class UsernameExistsException extends CustomException {

	private String message;

	public UsernameExistsException() {
		this.message = "用户名已经存在";
	}

	public UsernameExistsException(String message) {
		super(message);
		this.message = message;
	}



	public String getMessage() {
		return message;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}

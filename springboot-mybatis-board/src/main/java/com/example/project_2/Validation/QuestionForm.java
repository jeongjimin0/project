package com.example.project_2.Validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionForm {

    @NotEmpty(message = "제목은 필수항목입니다.") /*Null 허용하지 않음 */
    @Size(max=200)
    private String SUBJECT;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String CONTENT;
}

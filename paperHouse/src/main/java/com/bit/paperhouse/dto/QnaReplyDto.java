package com.bit.paperhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaReplyDto  implements Serializable {

    private int replySeq;
    private int qnaSeq;
    private int userSeq;

    private String title;
    private String cont;
    private String replyDate;
}

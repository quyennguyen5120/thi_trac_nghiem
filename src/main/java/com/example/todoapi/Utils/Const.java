package com.example.todoapi.Utils;

import javax.persistence.criteria.CriteriaBuilder;

public class Const {

    public static enum TypeAnsewr{
        SINGLE_ANWSER(1),
        MUTILIP_ANWSER(2),
        SELECT_ANWSER(3);

        private Integer value;
        private TypeAnsewr(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}

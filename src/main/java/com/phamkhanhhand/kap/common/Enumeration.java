package com.phamkhanhhand.kap.common;

import java.util.Objects;

public class Enumeration {


    public class Scopes {

        public static final String ADD = "ADD";
        public static final String EDIT = "EDIT";
        public static final String DELETE = "DELETE";
        public static final String VIEW = "VIEW";
        public static final String IMPORT = "IMPORT";
        public static final String APPROVE = "APPROVE";
        public static final String COMPLETE = "COMPLETE";
        public static final String CANCEL = "CANCEL";


        public static final String SPECIAL_ACTION_1 = "SPECIAL_ACTION_1";
        public static final String SPECIAL_ACTION_2 = "SPECIAL_ACTION_2";
        public static final String SPECIAL_ACTION_3 = "SPECIAL_ACTION_3";

        // Private constructor để không bị khởi tạo
        private Scopes() {}
    }

    /**
     * SCOPE api
     */

    public enum Scope1 {

        ADD("ADD"),
        EDIT("EDIT"),
        DELETE("DELETE"),
        VIEW("VIEW"),
        IMPORT("IMPORT"),
        APPROVE("APPROVE");

        private final String value;

        Scope1(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        // Nếu cần lấy enum từ giá trị string
        public static Scope1 fromValue(String value) {
            for (Scope1 scope : Scope1.values()) {
                if (scope.value.equalsIgnoreCase(value)) {
                    return scope;
                }
            }
            throw new IllegalArgumentException("Unknown scope value: " + value);
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public class AdjustmentTabType {

        /*
         * ADD-SUB NEW BUDGET LINE/ THEM-GIAM NGAN SACH
         */
        public static final String ADDITION = "ADDITION";

        /*
         * TRANSFER THIS LINE TO THAT LINE/DIEU CHUYEN HANG MUC TU MUC NAY SANG MUC KIA
         */
        public static final String TRANSFER = "TRANSFER";

    }


    public class Flag {

        /*
         * Yes
         */
        public static final String Y = "Y";

        /*
         * No
         */
        public static final String N = "N";


        public static final String PASS = "PASS";
        public static final String FAIL = "FAIL";
        public static final String SUCCESS = "SUCCESS";

    }



    public class ChangeType {

        /*
         * UP
         */
        public static final String UP = "UP";

        /*
         * DOWN
         */
        public static final String DOWN = "DOWN";


    }



    public class RequestFormStatus {

        /*
        * create new/ tạo mới
         */
        public static final String CREATE = "A01";

        /*
        * submit to leader/trình lên lãnh đạo
         */
        public static final String SUBMIT = "A02";

        /*
        * leader rejected
         */
        public static final String REJECT = "A03";

        /*
         * leader approved
         */
        public static final String APPROVE = "A04";

        /*
         * coordination rejected
         */
        public static final String REJECT_COORDINATOR = "A05";

        /*
         * coordination approve
         */
        public static final String COMPLETE = "A06";


        /*
         * cancel
         */
        public static final String CANCEL = "A07";
    }


    public class AdjustmentStatus {

        /*
         * create new/ tạo mới
         */
        public static final String CREATE = "A01";

        /*
         * submit to leader/trình lên lãnh đạo
         */
        public static final String SUBMIT = "A02";

        /*
         * leader rejected
         */
        public static final String REJECT = "A03";

        /*
         * leader approved
         */
        public static final String APPROVE = "A04";

        /*
         * coordination rejected
         */
        public static final String REJECT_COORDINATOR = "A05";

        /*
         * coordination approve
         */
        public static final String COMPLETE = "A06";


        /*
         * cancel
         */
        public static final String CANCEL = "A07";
    }


    public enum ApproveAction {

        APPROVE(1),
        REJECT(2);
        private final int value;

        ApproveAction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        // Nếu cần lấy enum từ giá trị string
        public static ApproveAction fromValue(int value) {
            for (ApproveAction action : ApproveAction.values()) {
                if (Objects.equals(action, value)) {
                    return action;
                }
            }
            throw new IllegalArgumentException("Unknown scope value: " + value);
        }

    }

    public class RevertType {

        /*
         *  revert amount and gap
         */
        public static final String ADJUSTMENT_ALL = "ADJUSTMENT_ALL";

        /*
        * just revert amount
         */
        public static final String ADJUSTMENT = "ADJUSTMENT";


        /*
         * just revert Gap
         */
        public static final String ADJUSTMENT_GAP = "ADJUSTMENT_GAP";
    }

    public class EntityState {

        public static final int NONE = 0;
        public static final int ADD = 1;
        public static final int EDIT = 2;
        public static final int DELETE = 3;

    }



}

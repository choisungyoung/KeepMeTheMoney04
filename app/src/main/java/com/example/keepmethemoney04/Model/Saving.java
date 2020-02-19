package com.example.keepmethemoney04.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Saving implements Serializable {
    private String dcls_month;
    private String fin_co_no ;
    private String kor_co_nm;
    private String fin_prdt_cd ;
    private String fin_prdt_nm;
    private String join_way	;
    private String mtrt_int;
    private String spcl_cnd;
    private String join_deny;
    private String join_member;
    private String etc_note;
    private int max_limit;
    private String dcls_strt_day;
    private String dcls_end_day;
    private String fin_co_subm_day;


    private String intr_rate_type;
    private String intr_rate_type_nm;
    private String rsrv_type;
    private String rsrv_type_nm;
    private String save_trm;
    private double intr_rate;
    private double intr_rate2;

    public int depth = 0;
    public Saving(String dcls_month, String fin_co_no, String kor_co_nm, String fin_prdt_cd, String fin_prdt_nm, String join_way, String mtrt_int, String spcl_cnd, String join_deny, String join_member, String etc_note, int max_limit, String dcls_strt_day, String dcls_end_day, String fin_co_subm_day) {
        this.dcls_month = dcls_month;
        this.fin_co_no = fin_co_no;
        this.kor_co_nm = kor_co_nm;
        this.fin_prdt_cd = fin_prdt_cd;
        this.fin_prdt_nm = fin_prdt_nm;
        this.join_way = join_way;
        this.mtrt_int = mtrt_int;
        this.spcl_cnd = spcl_cnd;
        this.join_deny = join_deny;
        this.join_member = join_member;
        this.etc_note = etc_note;
        this.max_limit = max_limit;
        this.dcls_strt_day = dcls_strt_day;
        this.dcls_end_day = dcls_end_day;
        this.fin_co_subm_day = fin_co_subm_day;
    }

    public Saving(String dcls_month, String fin_co_no, String kor_co_nm, String fin_prdt_cd, String fin_prdt_nm, String join_way, String mtrt_int, String spcl_cnd, String join_deny, String join_member, String etc_note, int max_limit, String dcls_strt_day, String dcls_end_day, String fin_co_subm_day, String intr_rate_type, String intr_rate_type_nm, String rsrv_type, String rsrv_type_nm, String save_trm, double intr_rate, double intr_rate2) {
        this.dcls_month = dcls_month;
        this.fin_co_no = fin_co_no;
        this.kor_co_nm = kor_co_nm;
        this.fin_prdt_cd = fin_prdt_cd;
        this.fin_prdt_nm = fin_prdt_nm;
        this.join_way = join_way;
        this.mtrt_int = mtrt_int;
        this.spcl_cnd = spcl_cnd;
        this.join_deny = join_deny;
        this.join_member = join_member;
        this.etc_note = etc_note;
        this.max_limit = max_limit;
        this.dcls_strt_day = dcls_strt_day;
        this.dcls_end_day = dcls_end_day;
        this.fin_co_subm_day = fin_co_subm_day;
        this.intr_rate_type = intr_rate_type;
        this.intr_rate_type_nm = intr_rate_type_nm;
        this.rsrv_type = rsrv_type;
        this.rsrv_type_nm = rsrv_type_nm;
        this.save_trm = save_trm;
        this.intr_rate = intr_rate;
        this.intr_rate2 = intr_rate2;
    }

    public Saving(String dcls_month, String fin_co_no, String fin_prdt_cd, String intr_rate_type, String intr_rate_type_nm, String rsrv_type, String rsrv_type_nm, String save_trm, double intr_rate, double intr_rate2) {
        this.dcls_month = dcls_month;
        this.fin_co_no = fin_co_no;
        this.fin_prdt_cd = fin_prdt_cd;
        this.intr_rate_type = intr_rate_type;
        this.intr_rate_type_nm = intr_rate_type_nm;
        this.rsrv_type = rsrv_type;
        this.rsrv_type_nm = rsrv_type_nm;
        this.save_trm = save_trm;
        this.intr_rate = intr_rate;
        this.intr_rate2 = intr_rate2;
    }

    public Saving(Saving s1, Saving s2) {
        this(s1.dcls_month, s1.fin_co_no, s1.kor_co_nm, s1.fin_prdt_cd, s1.fin_prdt_nm, s1.join_way, s1.mtrt_int, s1.spcl_cnd, s1.join_deny, s1.join_member, s1.etc_note, s1.max_limit, s1.dcls_strt_day, s1.dcls_end_day, s1.fin_co_subm_day, s2.intr_rate_type, s2.intr_rate_type_nm, s2.rsrv_type, s2.rsrv_type_nm, s2.save_trm, s2.intr_rate, s2.intr_rate2);
    }

    @Override
    public String toString() {
        return "Saving{" +
                "dcls_month='" + dcls_month + '\'' +
                ", fin_co_no='" + fin_co_no + '\'' +
                ", kor_co_nm='" + kor_co_nm + '\'' +
                ", fin_prdt_cd='" + fin_prdt_cd + '\'' +
                ", fin_prdt_nm='" + fin_prdt_nm + '\'' +
                ", join_way='" + join_way + '\'' +
                ", mtrt_int='" + mtrt_int + '\'' +
                ", spcl_cnd='" + spcl_cnd + '\'' +
                ", join_deny='" + join_deny + '\'' +
                ", join_member='" + join_member + '\'' +
                ", etc_note='" + etc_note + '\'' +
                ", max_limit=" + max_limit +
                ", dcls_strt_day='" + dcls_strt_day + '\'' +
                ", dcls_end_day='" + dcls_end_day + '\'' +
                ", fin_co_subm_day='" + fin_co_subm_day + '\'' +
                ", intr_rate_type='" + intr_rate_type + '\'' +
                ", intr_rate_type_nm='" + intr_rate_type_nm + '\'' +
                ", rsrv_type='" + rsrv_type + '\'' +
                ", rsrv_type_nm='" + rsrv_type_nm + '\'' +
                ", save_trm='" + save_trm + '\'' +
                ", intr_rate=" + intr_rate +
                ", intr_rate2=" + intr_rate2 +
                '}';
    }

    public String getDcls_month() {
        return dcls_month;
    }

    public void setDcls_month(String dcls_month) {
        this.dcls_month = dcls_month;
    }

    public String getFin_co_no() {
        return fin_co_no;
    }

    public void setFin_co_no(String fin_co_no) {
        this.fin_co_no = fin_co_no;
    }

    public String getKor_co_nm() {
        return kor_co_nm;
    }

    public void setKor_co_nm(String kor_co_nm) {
        this.kor_co_nm = kor_co_nm;
    }

    public String getFin_prdt_cd() {
        return fin_prdt_cd;
    }

    public void setFin_prdt_cd(String fin_prdt_cd) {
        this.fin_prdt_cd = fin_prdt_cd;
    }

    public String getFin_prdt_nm() {
        return fin_prdt_nm;
    }

    public void setFin_prdt_nm(String fin_prdt_nm) {
        this.fin_prdt_nm = fin_prdt_nm;
    }

    public String getJoin_way() {
        return join_way;
    }

    public void setJoin_way(String join_way) {
        this.join_way = join_way;
    }

    public String getMtrt_int() {
        return mtrt_int;
    }

    public void setMtrt_int(String mtrt_int) {
        this.mtrt_int = mtrt_int;
    }

    public String getSpcl_cnd() {
        return spcl_cnd;
    }

    public void setSpcl_cnd(String spcl_cnd) {
        this.spcl_cnd = spcl_cnd;
    }

    public String getJoin_deny() {
        return join_deny;
    }

    public void setJoin_deny(String join_deny) {
        this.join_deny = join_deny;
    }

    public String getJoin_member() {
        return join_member;
    }

    public void setJoin_member(String join_member) {
        this.join_member = join_member;
    }

    public String getEtc_note() {
        return etc_note;
    }

    public void setEtc_note(String etc_note) {
        this.etc_note = etc_note;
    }

    public int getMax_limit() {
        return max_limit;
    }

    public void setMax_limit(int max_limit) {
        this.max_limit = max_limit;
    }

    public String getDcls_strt_day() {
        return dcls_strt_day;
    }

    public void setDcls_strt_day(String dcls_strt_day) {
        this.dcls_strt_day = dcls_strt_day;
    }

    public String getDcls_end_day() {
        return dcls_end_day;
    }

    public void setDcls_end_day(String dcls_end_day) {
        this.dcls_end_day = dcls_end_day;
    }

    public String getFin_co_subm_day() {
        return fin_co_subm_day;
    }

    public void setFin_co_subm_day(String fin_co_subm_day) {
        this.fin_co_subm_day = fin_co_subm_day;
    }

    public String getIntr_rate_type() {
        return intr_rate_type;
    }

    public void setIntr_rate_type(String intr_rate_type) {
        this.intr_rate_type = intr_rate_type;
    }

    public String getIntr_rate_type_nm() {
        return intr_rate_type_nm;
    }

    public void setIntr_rate_type_nm(String intr_rate_type_nm) {
        this.intr_rate_type_nm = intr_rate_type_nm;
    }

    public String getRsrv_type() {
        return rsrv_type;
    }

    public void setRsrv_type(String rsrv_type) {
        this.rsrv_type = rsrv_type;
    }

    public String getRsrv_type_nm() {
        return rsrv_type_nm;
    }

    public void setRsrv_type_nm(String rsrv_type_nm) {
        this.rsrv_type_nm = rsrv_type_nm;
    }

    public String getSave_trm() {
        return save_trm;
    }

    public void setSave_trm(String save_trm) {
        this.save_trm = save_trm;
    }

    public double getIntr_rate() {
        return intr_rate;
    }

    public void setIntr_rate(double intr_rate) {
        this.intr_rate = intr_rate;
    }

    public double getIntr_rate2() {
        return intr_rate2;
    }

    public void setIntr_rate2(double intr_rate2) {
        this.intr_rate2 = intr_rate2;
    }
    public class SavingPair{
        public String title;
        public String content;

        public SavingPair(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    public ArrayList<SavingPair> getArrayList(){
        ArrayList<SavingPair> list = new ArrayList<>();

        list.add(new SavingPair("금융회사 명", kor_co_nm));
        list.add(new SavingPair("가입방법", join_way));
        list.add(new SavingPair("가입대상", join_member));
        list.add(new SavingPair("저축 금리", intr_rate+""));
        list.add(new SavingPair("최고 우대금리", intr_rate2+""));
        list.add(new SavingPair("최고한도", max_limit+""));
        list.add(new SavingPair("저축 기간", save_trm));
        list.add(new SavingPair("저축 금리 유형명", intr_rate_type_nm));
        list.add(new SavingPair("적립 유형명", rsrv_type_nm));
        list.add(new SavingPair("우대조건", spcl_cnd));
        list.add(new SavingPair("만기 후 이자율", mtrt_int));

        return list;
    }
}

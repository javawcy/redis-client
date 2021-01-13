package dev.lowdad.libs.redisclient;

/**
 * <p>
 * zSet成员解析
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class StringTuple {
    private String member;
    private double score;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

package Entity;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * member: class which can save some information of member object
 * @author: Seo, HyeongCheol
 */
public class Member {
    private long memberId;
    private String name;
    private Date birthday;
    private String email;
    private PositionList position; // 대표 - President, 총무 - Administrator, 부원 - employee

    public Member(){ // default Constructor
        setMemberId(0); // ID - 0 : is never used. (ID Start from 1)
        setName("None");
        setBirthday(new Date());
        setEmail("None");
        setPosition(PositionList.NONE);
    }

    public Member(long memberId, String name, int year, int month, int day, String email, PositionList position) { // Constructor using Information
        setMemberId(memberId);
        setName(name);
        setBirthday(new Date(year, month, day));
        setEmail(email);
        setPosition(position);
    }

    public Member(Member otherObj){ // copy constructor
        setMemberId(otherObj.getMemberId());
        setName(otherObj.getName());
        setBirthday(new Date(otherObj.getBirthday()));
        setEmail(getEmail());
        setPosition(getPosition());
    }

    public long getMemberId(){ return this.memberId; }
    public String getName(){ return this.name; }
    public Date getBirthday() {return new Date(this.birthday); }
    public String getEmail() { return this.email; }
    public PositionList getPosition() { return this.position; }
    private void setMemberId(long memberId) { this.memberId = memberId; }
    private void setName(String name) { this.name = name; }
    private void setBirthday(Date birthday) { this.birthday = new Date(birthday); }
    private void setEmail(String email) { this.email = email; }
    private void setPosition(PositionList position) { this.position = position; }

}

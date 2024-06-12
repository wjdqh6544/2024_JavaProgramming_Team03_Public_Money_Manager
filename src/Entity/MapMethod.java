package Entity;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * HashMapMethod: class which contains methods relative to TreeMap
 * @author: Seo, HyeongCheol
 */
public class MapMethod {
    public String getKey(Object obj) {
        if (obj instanceof abs_Member) {
            return ((abs_Member) obj).getName() + ((abs_Member) obj).getEmail();
        } else if (obj instanceof MemberPosition) {
            return ((MemberPosition) obj).getGroupName();
        } else if (obj instanceof Group){
            return ((Group) obj).getGroupName();
        }
        return null;
    }
}

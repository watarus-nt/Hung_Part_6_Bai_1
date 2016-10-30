package common;

/**
 * Created by WataruS on 10/22/2016.
 */
public class Global {
    public enum Title {
        NOT_DEFINE, ENGINEER, SENIOR_ENGINEER, CONSULTANT, SENIOR_CONSULTANT, PRINCIPAL
    }

    public enum TMAWorkingTeam {
        SecureLogix, BV02, SMS, AMDOCS, DA, TechOne
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }
    public enum Description {
        CONFIDENT, SENSITIVE, CRAZY,
        KIND, MEAN, SERIOUS, HONEST,
        DISHONEST, CLEVER, OUTGOING, SHY,
        POLITE, FUNNY, BORING, FRIENDLY,
        CUTE, LAZY, SMART
    }
    public enum Relationship {
        HUSBAND, WIFE, MOTHER, FATHER, SON, DAUGHTER, BROTHER, SISTER
    }
    public enum MaleFirstName {
        HUNG, PHU, CHIEN, TU, DANH, TUYEN, HOANG, TRUNG, BAO, THUAN,
        NAM, TINH, ANH, LY, DIEP, KHANH, BINH, THAI, HUY, DUC
    }

    public enum FemaleFirstName {
        TUYET, TUYEN, THUONG, KHUYEN, HANH, DUNG, YEN, NGAN, DIEU, HONG,
        HANG, AN, PHUC, THAO, LIEN, HUONG, TRINH, LINH, TRAM, PHUONG
    }

    public enum LastName {
        TRAN, NGUYEN, LE, DOAN, VO, VU, LA, LY, PHAM, DO,
        VUONG, DAU, HUYNH, PHAN, HO, PHUONG, TRA, VIEN, CAO, NGO
    }

    public enum Company {
        BIDV, GlobalCyber_Soft, HSBC, MB, TechcomBank, DongA, VietcomBank, Aribank
    }

    public enum RelativeRole{
        WIFE, DAUGHTER, GIRLFRIEND, FRIEND, MOTHER, AUNT, GRANDMOTHER, FIANCE
    }


    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

}

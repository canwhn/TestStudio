package net.canwhn.annotation;
 /**
 * ö�٣�ģ��ע�������ö������
 *
 */
 public enum Gender {
    MAN{
        public String getName(){return "��";}
    },
    WOMEN{
        public String getName(){return "Ů";}
    }; //�ǵ��С�;��
     public abstract String getName();
}
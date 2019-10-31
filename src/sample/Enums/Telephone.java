package sample.Enums;

public enum Telephone{
    Рабочий{
        @Override
        public String toString(){
            return "Рабочий";
        }
    },
    Мобильный{
        @Override
        public String toString(){
            return "Мобильный";
        }
    },
    Домашний{
        @Override
        public String toString(){
            return "Домашний";
        }
    },
    Неизвестно{
        @Override
        public String toString(){
            return "Неизвестно";
        }
    }
}

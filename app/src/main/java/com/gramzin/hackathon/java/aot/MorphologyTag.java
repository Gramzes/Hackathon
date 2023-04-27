package com.gramzin.hackathon.java.aot;

/**
 * Морфологическая характеристика для слова. Например {@link MorphologyTag#FirstPerson}
 */
public enum MorphologyTag {

    /**
     * Множественное число
     */
    Plural("мн.ч."),

    /**
     * Мужской род
     */
    Male("мр"),

    /**
     * Именительный падеж
     */
    Nominative("И.п."),

    /**
     * Местоимение-существительное
     */
    Pronoun("МЕСТ-СУЩ"),

    /**
     * Аббревиатура
     */
    Abbreviation("аббр"),

    /**
     * Краткое прилагательное
     */
    ShortAdjective("КР_ПРИЛ"),

    /**
     * Сравнительная степень (для прилагательных)
     */
    Comparative("сравн"),

    /**
     * Дательный падеж
     */
    Dative("Д.п."),

    /**
     * Настоящее время
     */
    PresentTense("наст.вр."),

    /**
     * Указательное наречие
     */
    IndicativeAdverb("указат"),

    /**
     * Совершенный вид
     */
    PerfectVerb("сов.вид"),

    /**
     * Отчество (Иванович, Михайлович)
     */
    MiddleName("отчество"),

    /**
     * Глагол в личной форме
     */
    Verb("личн."),

    /**
     * Прошедшее время
     */
    PastTime("прош.вр."),

    /**
     * Междометие
     */
    Interjection("МЕЖД"),

    /**
     * Наречие
     */
    Adverb("НАРЕЧИЕ"),

    /**
     * Единственное число
     */
    Singular("ед.ч"),

    /**
     * Сравнительная степень (для прилагательных)
     */
    NeuterGender("сравн."),

    /**
     * Прилагательное
     */
    Adjective("ПРИЛ"),

    /**
     * Существительное
     */
    Noun("СУЩ"),

    /**
     * Неодушевленное
     */
    Inanimate("не одуш."),

    /**
     * Организация
     */
    Organization("орг"),

    /**
     * Вводное слово
     */
    Introduction("ВВОДН"),

    /**
     * Непереходный глагол
     */
    Intransitive("не перех."),

    /**
     * Несовершенный вид
     */
    Imperfect("несов.вид"),

    /**
     * Архаизм
     */
    Archaism("арх"),

    /**
     * Топоним (Москва, Лена, Эверест)
     */
    Toponym("лок"),

    /**
     * Общий род (сирота, пьяница)
     */
    MaleFemale("мр-жр"),

    /**
     * Местоимение-предикатив
     */
    PronounPredicative("МС-ПРЕДК"),

    /**
     * Первое лицо
     */
    FirstPerson("1л"),

    /**
     * Безличный глагол (2)
     */
    ImpersonalVerb2("*"),

    /**
     * Страдательный залог
     */
    PassiveParticiple("стр.зал."),

    /**
     * Творительный падеж
     */
    Ablative("Т.п."),

    /**
     * Неизменяемое
     */
    Immutable("0"),

    /**
     * Второй родительный или второй предложный падежи
     */
    SecondGenetive("2 Р.п./Д.п"),

    /**
     * Разговорный
     */
    ColloquialSpeech("разг"),

    /**
     * Одушевленное
     */
    Animated("одуш."),

    /**
     * Опечатка
     */
    Typo("опч"),

    /**
     * Местоименное прилагательное
     */
    PronounAdjective("МС-П"),

    /**
     * Причастие
     */
    Participle("ПРИЧАСТИЕ"),

    /**
     * Превосходная степень (для прилагательных)
     */
    SuperlativeAdjective("превосх."),

    /**
     * Фамилия (Иванов, Сидоров)
     */
    Surname("фамилия"),

    /**
     * Качественное прилагательное
     */
    QualitativeAdjective("качеств."),

    /**
     * Действительный залог
     */
    ActiveVoice("действ.зал."),

    /**
     * Деепричастие
     */
    VerbalParticiple("ДЕЕПРИЧАСТИЕ"),

    /**
     * Краткое причастие
     */
    BriefCommunion("КР_ПРИЧАСТИЕ"),

    /**
     * Прилагательное (?)
     */
    AdjectiveUnusedType("дфст"),

    /**
     * Второе лицо
     */
    SecondPerson("2л"),

    /**
     * Женский род
     */
    Female("жр"),

    /**
     * Винительный падеж
     */
    Accusative("В.п."),

    /**
     * Будущее время
     */
    FutureTime("будущ.вр."),

    /**
     * Вопросительное наречие
     */
    InterrogativeAdverb("вопросит."),

    /**
     * Некая часть речи (?)
     */
    POSL_PART_OF_SPEECH("ПОСЛ"),

    /**
     * Переходный глагол
     */
    Transive("перех."),

    /**
     * Жаргонизм
     */
    Slang("жарг"),

    /**
     * Повелительное наклонение (императив)
     */
    Imperative("императив"),

    /**
     * Числительное (количественное)
     */
    Numeral("ЧИСЛ"),

    /**
     * Предикатив
     */
    Predicative("ПРЕДИКАТИВ"),

    /**
     * Частица
     */
    Particle("ЧАСТИЦА"),

    /**
     * Предлог
     */
    Pretext("ПРЕДЛОГ"),

    /**
     * Звательный падеж (Отче, Боже)
     */
    Vocative("З.п."),

    /**
     * Имя (Иван, Михаил)
     */
    Name("имя"),

    /**
     * Предложный падеж
     */
    Prepositional("П.п."),

    /**
     * Третье лицо
     */
    ThirdPerson("3л"),

    /**
     * Инфинитив
     */
    Infinitive("ИНФИНИТИВ"),

    /**
     * Безличный глагол
     */
    InpersonalVerb("безлич."),

    /**
     * Притяжательное
     */
    Superlative("притяжат."),

    /**
     * Порядковое числительное
     */
    OrdinalNumber("ЧИСЛ-П"),

    /**
     * Фразеологизм
     */
    Idiom("ФРАЗ"),

    /**
     * Родительный падеж
     */
    Genitive("Р.п."),

    /**
     * Союз
     */
    Union("СОЮЗ");

    private final String token;

    MorphologyTag(final String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
}
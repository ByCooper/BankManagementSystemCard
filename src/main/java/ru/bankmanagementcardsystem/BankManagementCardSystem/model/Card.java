package ru.bankmanagementcardsystem.BankManagementCardSystem.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberCard;
    @ManyToOne
    private User cardholder;
    private int dateActive;
    @Enumerated(EnumType.STRING)
    private StatusCard statusCard;
    private Double cardBalance;

    public Card(CardBuilder cardBuilder) {
        this.numberCard = cardBuilder.numberCard;
        this.cardholder = cardBuilder.cardholder;
        this.dateActive = cardBuilder.dateActive;
        this.statusCard = cardBuilder.statusCard;
        this.cardBalance = cardBuilder.cardBalance;
        this.id = (long) (Math.random() * Long.MAX_VALUE);
    }

    public Long getId() {
        return id;
    }

    public String getNumberCard() {
        return maskCardNumber(numberCard);
    }

    public User getCardholder() {
        return cardholder;
    }

    public int getDateActive() {
        return dateActive;
    }

    public StatusCard getStatusCard() {
        return statusCard;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

//    public void setStatusCard(StatusCard statusCard) {
//        this.statusCard = statusCard;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return dateActive == card.dateActive && Objects.equals(numberCard, card.numberCard) && Objects.equals(cardholder, card.cardholder) && statusCard == card.statusCard && Objects.equals(cardBalance, card.cardBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberCard, cardholder, dateActive, statusCard, cardBalance);
    }

    @Override
    public String toString() {
        return "Card{" +
                "numberCard=" + maskCardNumber(numberCard) +
                ", cardholder='" + cardholder + '\'' +
                ", dateActive=" + dateActive +
                ", statusCard=" + statusCard +
                ", cardBalance=" + cardBalance +
                '}';
    }

    public static CardBuilder builder(){
        return new CardBuilder();
    }

    public static CardBuilder builder(Card card){
        return new CardBuilder().from(card);
    }

    public static class CardBuilder {
        private String numberCard;
        private User cardholder;
        private int dateActive;
        private StatusCard statusCard;
        private Double cardBalance;

        public CardBuilder from(Card card){
            this.numberCard = card.getNumberCard();
            this.cardholder = card.getCardholder();
            this.dateActive = card.getDateActive();
            this.statusCard = card.getStatusCard();
            this.cardBalance = card.getCardBalance();
            return this;
        }

        public CardBuilder numberCard(String numberCard) {
            this.numberCard = numberCard;
            return this;
        }

        public CardBuilder statusCard(StatusCard statusCard) {
            this.statusCard = statusCard;
            return this;
        }

        public CardBuilder cardBalance(Double cardBalance) {
            this.cardBalance = cardBalance;
            return this;
        }

        public CardBuilder dateActive(int dateActive) {
            this.dateActive = dateActive;
            return this;
        }

        public CardBuilder cardholder(User cardholder) {
            this.cardholder = cardholder;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }


    public static String maskCardNumber(String numberCard) {

        if (numberCard == null || numberCard.length() < 13) {
            return numberCard;
        }

        StringBuilder result = new StringBuilder();
        int digitCount = 0;

        for (char c : numberCard.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
                if (digitCount > 6 && digitCount <= numberCard.replaceAll("[^0-9]", "").length() - 4) {
                    result.append('*');
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

}

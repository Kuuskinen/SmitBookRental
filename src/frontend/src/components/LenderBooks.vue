<template>
  <div>
    <h1>Minu laenatud raamatud</h1>
    <div class="container">
      <div v-if="info == null">
        <p>Raamatuid ei leitud</p>
      </div>
      <div class="row headline" v-if="info != null">
        <div class="col-md-2">Raamatu nimi</div>
        <div class="col-md-2">Raamatu autor</div>
        <div class="col-md-2">Broneeringu lõppkuupäev</div>
        <div class="col-md-2">Staatus</div>
        <div class="col-md-2">Muuda staatust</div>
        <div class="col-md-2">Kustuta reserveering</div>
      </div>
      <div v-for="book in info" :key="book.id" class="bookRow row">
        <div class="col-md-2">{{book.name}}</div>
        <div class="col-md-2">{{book.author}}</div>
        <div class="col-md-2">{{book.author}}</div>
        <!--<div class="col-md-2">{{book.endDate}}</div>-->
        <div class="col-md-2">{{book.status}}</div>
        <div v-if="book.status === 'Sent'" class="col-md-2"><button class="btn btn-primary" @click="changeStatus(book.id)">
          Kätte saadud</button></div>
        <div v-if="book.status === 'Received'" class="col-md-2"><button class="btn btn-primary" @click="changeStatus(book.id)">
          Tagastatud</button></div>
        <div v-else class="col-md-2">Pole lubatud</div>
        <div v-if="book.status === 'Reserved'" class="col-md-2"><button class="btn btn-danger"
                                                                        @click="deleteBookReservation(book.id)">
          KUSTUTA</button></div>
      </div>
    </div>
  </div>
</template>

<script>
import rentalService from "@/rentalService.ts";
import axios from "axios";

export default {
  name: "LenderBooks",
  data() {
    return{ info: null}
  },
  mounted: async function () {
    await rentalService.getMyBorrowedBooks(1).then(response => (this.info = response.data));
  },
  methods: {
    changeStatus(bookId) {
      axios.post('/book_status_update', {id: bookId})
      window.location.reload();
    },
    deleteBookReservation(bookId) {
      axios.post('/delete_book_borrowed', {id: bookId})
      window.location.reload();
    }
  }
}
</script>

<style scoped>

</style>

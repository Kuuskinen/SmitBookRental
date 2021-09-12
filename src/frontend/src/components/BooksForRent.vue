<template>
  <div>
    <h1>Raamatud, mida laenutada</h1>
    <div class="container">
      <div v-if="info == null">
        <p>Raamatuid ei leitud</p>
      </div>
      <div v-if="info != null" class="headline row">
        <div class="col-md-4">Raamatu nimi</div>
        <div class="col-md-4">Raamatu autor</div>
        <div class="col-md-4">Broneerimine</div>
      </div>
      <div v-for="book in info" :key="book.id" class="bookRow row">
        <div class="col-md-4">{{book.name}}</div>
        <div class="col-md-4">{{book.author}}</div>
        <div class="col-md-4"><button class="btn btn-primary" @click="gotoReserving(book.id)">
          Broneeri raamat</button></div>
      </div>
    </div>
  </div>
</template>

<script>
import rentalService from "@/rentalService.ts";

export default {
  name: "BooksForRent",
  data() {
    return{ info: null}
  },
  async mounted() {
    await rentalService.getBooks().then(response => (this.info = response.data));
  },
  methods: {
    gotoReserving(id) {
      console.log(id);
      this.$router.push({name: 'rentBook', params: { id: id }})
    }
  }
}
</script>

<style scoped>
.headline {
  font-weight: bold;
}

.bookRow {
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: 2px solid gray;
}
</style>

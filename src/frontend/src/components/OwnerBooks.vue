<template>
  <div>
    <h1>Minu raamatud</h1>
    <div class="container">
      <div v-if="info == null">
        <p>Raamatuid ei leitud</p>
      </div>
      <div class="row headline" v-if="info != null">
        <div class="col-md-3">Raamatu nimi</div>
        <div class="col-md-3">Raamatu autor</div>
        <div class="col-md-3">Laenutatav?</div>
        <div class="col-md-3">Õigused ja broneeringud</div>
      </div>
      <div v-for="book in info" :key="book.id" v-bind:class="{ active: book.status === 'Reserved' }" class="bookRow row">
        <div class="col-md-3">{{book.name}}</div>
        <div class="col-md-3">{{book.author}}</div>
        <div v-if="book.reservable === true" class="col-md-3">Jah</div>
        <div v-else class="col-md-3">Ei ole</div>
        <div class="col-md-3"><button class="btn btn-primary" @click="gotoEdit(book.id)">
        Muuda raamatut</button></div>
      </div>
    </div>
  </div>
</template>

<script>
import rentalService from "@/rentalService";

export default {
  name: "BooksToRent",
  data() {
    return{ info: null}
  },
  mounted: async function () {
    await rentalService.getOwnerBooks(1).then(response => (this.info = response.data));
  },
  methods: {
    gotoEdit(id) {
      console.log(id);
      this.$router.push({name: 'changeBook', params: { id: id }})
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

.active {
  background-color: #a6a6a6;
}
</style>

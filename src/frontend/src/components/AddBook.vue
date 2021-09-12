<template>
  <div>
    <h1>Lisa uus raamat</h1>
    <div class="container">
      <form v-on:submit.prevent="submitForm">
        <div class="row form-group">
          <label for="name">Raamatu nimi</label>
          <input type="text" class="form-control" id="name" placeholder="Lisatava raamatu nimi" v-model="form.name">
        </div>

        <div class="row form-group">
          <label for="author">Autor</label>
          <input type="text" class="form-control" id="author" placeholder="Lisatava raamatu autor"
                 v-model="form.author">
        </div>

        <div class="row form-group">
          <label>Laenutatav?</label><br />
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="reservable" id="reservable-yes" value="true"
                   v-model="form.reservable">
            <label class="form-check-label">Jah</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="notReservable" id="reservable-no" value="false"
                   v-model="form.reservable">
            <label class="form-check-label">Ei ole</label>
          </div>
        </div>

        <div class="form-group">
          <button class="btn btn-success">Lisa raamat</button>
        </div>
      </form>

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AddBook",
  data() {
    return {
      form: {
        name:'',
        author:'',
        reservable:''
      }
    }
  },
  methods: {
    submitForm() {
      axios.post('/new_book', this.form)
      window.location.reload();
    }
  }
}
</script>

<style scoped>

</style>

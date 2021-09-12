<template>
  <div>
    <h1>Raamatu laenutamine</h1>
    <div class="container">
      <div class="bookInfo">
        <h3>Raamatu info</h3>
        <div class="headline row">
          <div class="col-md-6">Raamatu nimi</div>
          <div class="col-md-6">Raamatu autor</div>
        </div>
        <div class="row">
          <div class="col-md-6">{{info.name}}</div>
          <div class="col-md-6">{{info.author}}</div>
        </div>

      </div>

      <div>
        <h3>Broneerimine</h3>
      </div>
      <div>
        <div>

          <form v-on:submit.prevent="submitForm">

            <div class="row form-group">
              <label for="endDate">Broneeringu lõpuaeg</label>
              <input type="date" class="form-control" id="endDate" v-model="form.endDate">
            </div>

            <div class="form-group">
              <button class="btn btn-success">Salvesta muudatused</button>
            </div>
          </form>

        </div>
      </div>

    </div>
  </div>
</template>

<script>
import rentalService from "@/rentalService";
import axios from "axios";

export default {
  name: "RentBook",
  data() {
    return{
      info: null,
      form: {
        bookId:'',
        lenderId: '',
        endDate:''
      }
    }
  },
  async mounted() {
    await rentalService.getOneBook(parseInt(this.$route.params.id)).then(response => {
      this.info = response.data
      this.form.bookId = this.info.id;
      this.form.lenderId = 1;
    });
  },
  methods: {
    submitForm() {
      axios.post('/new_reservation', this.form)
    }
  }
}
</script>

<style scoped>
h3 {
  padding-top: 15px;
  border-bottom: 2px solid gray;
}

.headline {
  font-weight: bold;
}

.bookInfo {
  padding-bottom: 15px;
}

</style>

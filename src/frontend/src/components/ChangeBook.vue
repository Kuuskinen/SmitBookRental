<template>
  <div>
    <h1>Raamatu muutmine ja broneeringud</h1>
    <div class="main_table container">
      <form v-on:submit.prevent="submitForm">
        <div class="row form-group">
          <label for="name">Raamatu nimi</label>
          <input type="text" class="form-control" id="name" v-bind:value="info.name" v-model="info.name">
        </div>

        <div class="row form-group">
          <label for="author">Autor</label>
          <input type="text" class="form-control" id="author" v-model="info.author">
        </div>

        <div class="row form-group">
          <label>Laenutatav?</label><br />
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="reservable" id="reservable-yes" value="true"
                   v-model="info.reservable">
            <label class="form-check-label">Jah</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="notReservable" id="reservable-no" value="false"
                   v-model="info.reservable">
            <label class="form-check-label">Ei ole</label>
          </div>
        </div>

        <div class="form-group">
          <button class="btn btn-success">Salvesta muudatused</button>
        </div>
      </form>
      <div>
        <h1>"{{info.name}}" broneeringud</h1>
      </div>

      <div class="col-md-12" v-if="reservation === '' || reservation == null">
        <p>Broneeringuid ei leitud</p>
      </div>
      <div v-if="reservation !== '' && reservation != null" class="headline row">
        <p>Broneering on kustutatav ainult siis kui see on staatuses "Reserveeritud". Hiljem broneeringut kustutada
          ei saa.</p>
        <p>Broneeringu staatust saab omanik uuendada kui staatuseks on "üleantud" või "tagastatud".</p>
      </div>
      <div v-if="reservation !== '' && reservation != null" class="headline row">
        <div class="col-md-2">Laenaja</div>
        <div class="col-md-2">Algusaeg</div>
        <div class="col-md-2">Lõpuaeg</div>
        <div class="col-md-2">Staatus</div>
        <div class="col-md-2">Staatuse muutmine</div>
        <div class="col-md-2">Kustutamine</div>
      </div>

      <div v-if="reservation !== '' && reservation != null" class="bookRow row">
        <div class="col-md-2">{{reservation.lenderId}}</div>
        <div class="col-md-2">{{reservation.startDate}}</div>
        <div class="col-md-2">{{reservation.endDate}}</div>
        <div class="col-md-2">{{info.status}}</div><!-- STAATUS -->
        <div v-if="info.status === 'Reserved'" class="col-md-2"><button class="btn btn-success" @click="changeStatus(info.id)">Üleantud</button></div>
        <div v-if="info.status === 'Returned'" class="col-md-2"><button class="btn btn-success" @click="changeStatus(info.id)">Tagastatud</button></div>
        <div v-else class="col-md-2">-</div>
        <div v-if="info.status === 'Reserved'" class="col-md-2"><button class="btn btn-danger" @click="deleteReservation(reservation.id, info.id)">KUSTUTA</button></div>
        <div v-else class="col-md-2">-</div>
      </div>
    </div>
  </div>
</template>

<script>
import rentalService from "@/rentalService";
import axios from "axios";

export default {
  name: "ChangeBook",
  data() {
    return{
      info: null,
      reservation: null
    }
  },
  async mounted() {
    await rentalService.getOneBook(parseInt(this.$route.params.id)).then(response => (this.info = response.data));
    await rentalService.getBookReservation(parseInt(this.$route.params.id)).then(response => (this.reservation = response.data));
    },
  methods: {
    submitForm() {
      axios.post('/change_book', this.info)
    },
    deleteReservation(reservationId, bookId) {
      axios.delete('/delete_reservation', {data: {reservationId: reservationId, bookId: bookId}})
      window.location.reload();
    },
    changeStatus(bookId) {
      axios.post('/book_status_update', {id: bookId})
      window.location.reload();
    }

  }
}

</script>

<style scoped>
.main_table {
  max-width: 600px;
}

.headline {
  font-weight: bold;
}
</style>

<template id="play">
  <app-frame>
    <div>
      Ruilbieb:

      <div v-if="summary">
        Totaal: {{summary.total}} <a href="listing">(lijst)</a>
      </div>


      <table>
        <tbody>
        <tr v-for="summ in summary.dagSummary">
          <td>{{summ.dag}}</td>
          <td>{{summ.count}}</td>
        </tr>
        </tbody>
      </table>


    </div>
  </app-frame>
</template>

<style>
</style>

<script>

Vue.component("play", {
  template: "#play",
  data: () => ({
    actions: {},
    summary: {}
  }),

  created() {
    this.load()
  },
  methods: {
    load: function (event) {
      fetch(`/api/game/summary`)
          .then(res => res.text())
          .then(text => this.summary = JSON.parse(text))
          .catch(err => alert(err));
    },
  }
});
</script>

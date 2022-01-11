<template id="summary">
  <app-frame>
    <div>
      Ruilbieb:
<!--      <div v-if="summary">-->
<!--      Totaal: {{summary.total}}-->
<!--      </div>-->

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

Vue.component("summary", {
  template: "#summary",
  data: () => ({
    summary: {},
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

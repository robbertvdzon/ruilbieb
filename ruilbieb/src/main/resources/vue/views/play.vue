<template id="play">
  <app-frame>
    <div>
      Ruilbieb:

            <div v-if="summary">
            Totaal: {{summary.total}}
            </div>


      <table>
        <tbody>
        <tr v-for="summ in summary.dagSummary">
          <td>{{summ.dag}}</td>
          <td>{{summ.count}}</td>
        </tr>
        </tbody>
      </table>


<!--      <table>-->
<!--        <tbody>-->
<!--        <tr v-for="action in actions">-->
<!--          <td>{{action.timestampString}}</td>-->
<!--          <td>{{action.actionString}}</td>-->
<!--        </tr>-->
<!--        </tbody>-->
<!--      </table>-->

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
      // fetch(`/api/game/load`)
      //     .then(res => res.text())
      //     .then(text => this.actions = JSON.parse(text))
      //     .catch(err => alert(err));
      fetch(`/api/game/summary`)
          .then(res => res.text())
          .then(text => this.summary = JSON.parse(text))
          .catch(err => alert(err));
    },
  }
});
</script>

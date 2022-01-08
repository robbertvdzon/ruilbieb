<template id="play">
  <app-frame>
    <div>
      Ruilbieb: {{status}}
    </div>
  </app-frame>
</template>

<style>
</style>

<script>

Vue.component("play", {
  template: "#play",
  data: () => ({
    status: "TEST",
  }),

  created() {
    this.load()
  },
  methods: {
    load: function (event) {
      fetch(`/api/game/load`)
          .then(res => res.text())
          .then(text => this.status = text)
          .catch(err => alert(err));
    },
  }
});
</script>
